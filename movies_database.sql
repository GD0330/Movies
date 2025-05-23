PGDMP      3        	        }           MovieCatalog    17.2    17.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16486    MovieCatalog    DATABASE     �   CREATE DATABASE "MovieCatalog" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Bulgarian_Bulgaria.1251';
    DROP DATABASE "MovieCatalog";
                     postgres    false            �           0    0    DATABASE "MovieCatalog"    ACL     /   GRANT ALL ON DATABASE "MovieCatalog" TO movie;
                        postgres    false    4816            �            1259    16489    administrator    TABLE     U   CREATE TABLE public.administrator (
    username text NOT NULL,
    password text
);
 !   DROP TABLE public.administrator;
       public         heap r       postgres    false            �           0    0    TABLE administrator    ACL     C   GRANT SELECT,INSERT,DELETE ON TABLE public.administrator TO movie;
          public               postgres    false    217            �            1259    16497    movie    TABLE     	  CREATE TABLE public.movie (
    id integer NOT NULL,
    rating double precision NOT NULL,
    genre character varying(50),
    platform character varying(50),
    language character varying(50),
    numberofwatchlists integer DEFAULT 0 NOT NULL,
    title text
);
    DROP TABLE public.movie;
       public         heap r       postgres    false            �           0    0    TABLE movie    ACL     B   GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.movie TO movie;
          public               postgres    false    219            �            1259    16496    movie_id_seq    SEQUENCE     �   CREATE SEQUENCE public.movie_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.movie_id_seq;
       public               postgres    false    219            �           0    0    movie_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.movie_id_seq OWNED BY public.movie.id;
          public               postgres    false    218            �           0    0    SEQUENCE movie_id_seq    ACL     4   GRANT ALL ON SEQUENCE public.movie_id_seq TO movie;
          public               postgres    false    218            �            1259    16559 	   watchlist    TABLE     �   CREATE TABLE public.watchlist (
    username character varying(255) NOT NULL,
    movie_id integer NOT NULL,
    id integer NOT NULL
);
    DROP TABLE public.watchlist;
       public         heap r       postgres    false            �           0    0    TABLE watchlist    ACL     ?   GRANT SELECT,INSERT,DELETE ON TABLE public.watchlist TO movie;
          public               postgres    false    220            �            1259    16579    wishlist_id_seq    SEQUENCE     �   CREATE SEQUENCE public.wishlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.wishlist_id_seq;
       public               postgres    false    220            �           0    0    wishlist_id_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE public.wishlist_id_seq OWNED BY public.watchlist.id;
          public               postgres    false    221            �           0    0    SEQUENCE wishlist_id_seq    ACL     @   GRANT SELECT,USAGE ON SEQUENCE public.wishlist_id_seq TO movie;
          public               postgres    false    221            *           2604    16500    movie id    DEFAULT     d   ALTER TABLE ONLY public.movie ALTER COLUMN id SET DEFAULT nextval('public.movie_id_seq'::regclass);
 7   ALTER TABLE public.movie ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    218    219            ,           2604    16580    watchlist id    DEFAULT     k   ALTER TABLE ONLY public.watchlist ALTER COLUMN id SET DEFAULT nextval('public.wishlist_id_seq'::regclass);
 ;   ALTER TABLE public.watchlist ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    221    220            �          0    16489    administrator 
   TABLE DATA           ;   COPY public.administrator (username, password) FROM stdin;
    public               postgres    false    217   �       �          0    16497    movie 
   TABLE DATA           a   COPY public.movie (id, rating, genre, platform, language, numberofwatchlists, title) FROM stdin;
    public               postgres    false    219          �          0    16559 	   watchlist 
   TABLE DATA           ;   COPY public.watchlist (username, movie_id, id) FROM stdin;
    public               postgres    false    220          �           0    0    movie_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.movie_id_seq', 10, true);
          public               postgres    false    218            �           0    0    wishlist_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.wishlist_id_seq', 21, true);
          public               postgres    false    221            .           2606    16495     administrator administrator_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.administrator
    ADD CONSTRAINT administrator_pkey PRIMARY KEY (username);
 J   ALTER TABLE ONLY public.administrator DROP CONSTRAINT administrator_pkey;
       public                 postgres    false    217            0           2606    16503    movie movie_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.movie DROP CONSTRAINT movie_pkey;
       public                 postgres    false    219            2           2606    16585    watchlist wishlist_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT wishlist_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY public.watchlist DROP CONSTRAINT wishlist_pkey;
       public                 postgres    false    220            3           2606    16569     watchlist wishlist_movie_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT wishlist_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 J   ALTER TABLE ONLY public.watchlist DROP CONSTRAINT wishlist_movie_id_fkey;
       public               postgres    false    4656    219    220            4           2606    16564     watchlist wishlist_username_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.watchlist
    ADD CONSTRAINT wishlist_username_fkey FOREIGN KEY (username) REFERENCES public.administrator(username);
 J   ALTER TABLE ONLY public.watchlist DROP CONSTRAINT wishlist_username_fkey;
       public               postgres    false    217    220    4654            �   %   x�K�L�J�L�J�L�J�L�JL�����\1z\\\ �k.      �   �   x�}��J�0��7O�'(���.㘙�tH�:"H��N���Ɓy{��(S����r�R�h{ �Զ�/�Į�m	1T���t��d�l?�[�ӫ��ɚ
 @����͘ٮףA	�Q
t�x#��o���q�`���T�FŠ�>g��6J�P��$��1��Ō����{�k��J���'�C��_J^UL�s�������CdQe#e��Q�O�)�A���dŗ��`pP�PED�?=k��"��7%�n�      �   N   x��1� C��0��G�..��hqy/mڎ
q����f����	�P�\�p*P�NhT-A>��~�I�6�<��     